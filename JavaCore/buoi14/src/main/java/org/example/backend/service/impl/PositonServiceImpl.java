package org.example.backend.service.impl;


import org.example.backend.repository.IPositionRepository;
import org.example.backend.repository.impl.PositionRepositoryImpl;
import org.example.backend.service.IPositionService;
import org.example.entity.Position;

import java.util.List;

public class PositonServiceImpl implements IPositionService {
    private IPositionRepository repository;

    public PositonServiceImpl() {
        this.repository = new PositionRepositoryImpl();
    }

    @Override
    public List<Position> findAll() {
        return repository.findAll();
    }
}
