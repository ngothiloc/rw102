package org.example.backend.repository;


import org.example.entity.Position;

import java.util.List;

public interface IPositionRepository {
    List<Position> findAll();
}
