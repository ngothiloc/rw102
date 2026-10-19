package backend.controller;


import backend.service.IPositionService;
import backend.service.impl.PositonServiceImpl;
import entity.Position;

import java.util.List;

public class PositionController {
    private IPositionService service;

    public PositionController() {
        this.service = new PositonServiceImpl();
    }

    public List<Position> findAll() {
        return service.findAll();
    }
}
