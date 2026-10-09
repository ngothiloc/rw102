package backend.service.impl;

import backend.repository.IDepartmentRepository;
import backend.repository.IPositionRepository;
import backend.repository.impl.PositionRepositoryImpl;
import backend.service.IPositionService;
import entity.Position;

import java.util.List;

public class PositionServiceImpl implements IPositionService {

    private IPositionRepository positionRepository;
    public  PositionServiceImpl() {
        positionRepository = new PositionRepositoryImpl();
    }
    @Override
    public List<Position> hienThiPos() {
        return positionRepository.hienThiPos();
    }
}
