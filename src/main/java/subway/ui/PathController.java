package subway.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import subway.application.PathService;
import subway.dto.AddPathRequest;

@RequestMapping("/lines/{line-id}/stations")
@RestController
public class PathController {

    private final PathService pathService;

    public PathController(final PathService pathService) {
        this.pathService = pathService;
    }

    @PostMapping
    public ResponseEntity<Void> addStationInPath(
            @PathVariable("line-id") final Long lineId,
            @RequestBody final AddPathRequest addPathRequest
    ) {
        pathService.addPath(
                lineId,
                addPathRequest.getTargetStationId(),
                addPathRequest.getAddStationId(),
                addPathRequest.getDistance(),
                addPathRequest.getDirection()
        );
        return ResponseEntity.ok().build();
    }

}
