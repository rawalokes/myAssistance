package com.info.myassistant.serviceimpl;

import com.info.myassistant.dto.ResponseDto;
import com.info.myassistant.dto.SourceDto;
import com.info.myassistant.model.Source;
import com.info.myassistant.repo.SourceRepo;
import com.info.myassistant.service.SourceService;
import com.info.myassistant.shared.BaseResponse;
import com.info.myassistant.utility.GetCurrentUserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:11:51 AM
 */
@Service
public class SourceServiceImpl extends BaseResponse implements SourceService {
    private final SourceRepo sourceRepo;
    private final GetCurrentUserDetails currentUserDetails;

    public SourceServiceImpl(SourceRepo sourceRepo, GetCurrentUserDetails currentUserDetails) {
        this.sourceRepo = sourceRepo;
        this.currentUserDetails = currentUserDetails;
    }

    @Override
    public ResponseDto create(SourceDto sourceDto) {
        if (sourceDto != null) {
            Source source = new Source(sourceDto);
            source.setUsers(currentUserDetails.getCurrentUser());
            sourceRepo.save(source);
            return successResponse("Source added successfully", null);
        }
        return errorResponse("Please Try again", null);
    }

    @Override
    public ResponseDto findByID(Integer integer) {
     Optional<Source> source=sourceRepo.findById(integer);
     if (source.isPresent()){
         return successResponse("",new SourceDto(source.get()));
     }
     return errorResponse("Source not found",null);
    }


    @Override
    public List<SourceDto> findAllSource() {
        List<Source> sources = sourceRepo.findAllSource(currentUserDetails
                .getCurrentUser(),false);
        return sources.stream().map(source -> new SourceDto(source))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseDto removeSource(Integer integer) {
        Optional<Source> source=sourceRepo.findById(integer);
        if (source.isPresent()){
            Source databaseSource= source.get();
            databaseSource.setDeleteStatus(true);
            sourceRepo.save(databaseSource);
            return successResponse("Source deleted successfully",null);
        }
        return errorResponse("Source not found",null);
    }


}
