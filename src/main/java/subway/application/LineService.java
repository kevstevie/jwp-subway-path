package subway.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subway.domain.Line;
import subway.domain.Station;
import subway.dto.LineRequest;
import subway.dto.LineResponse;
import subway.dto.LineWithStationResponse;
import subway.persistence.dao.LineDao;
import subway.persistence.repository.SubwayRepository;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class LineService {
    private final LineDao lineDao;
    private final SubwayRepository subwayRepository;

    public LineService(final LineDao lineDao, final SubwayRepository subwayRepository) {
        this.lineDao = lineDao;
        this.subwayRepository = subwayRepository;
    }

    public LineResponse saveLine(LineRequest request) {
        Line persistLine = lineDao.insert(new Line(request.getName(), request.getColor()));
        return LineResponse.of(persistLine);
    }

    public List<LineResponse> findLineResponses() {
        List<Line> persistLines = findLines();
        return persistLines.stream()
                .map(LineResponse::of)
                .collect(Collectors.toList());
    }

    public List<Line> findLines() {
        return lineDao.findAll();
    }

    public void updateLine(Long id, LineRequest lineUpdateRequest) {
        lineDao.update(new Line(id, lineUpdateRequest.getName(), lineUpdateRequest.getColor()));
    }

    public void deleteLineById(Long id) {
        lineDao.deleteById(id);
    }

    public LineWithStationResponse findLineById(final Long id) {
        final Line line = subwayRepository.findLine(id);
        final List<Station> stations = line.sortStations();
        return LineWithStationResponse.from(line, stations);
    }

    public List<LineWithStationResponse> findAllLines() {
        final List<Line> lines = subwayRepository.findLines();
        return lines.stream()
                .map(line -> LineWithStationResponse.from(line, line.sortStations()))
                .collect(Collectors.toList());
    }
}
