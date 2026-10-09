package backend.service;

import entity.CanBo;

import java.util.List;

public interface ICanBoService {
    List<CanBo> findAll();

    List<CanBo> findByName(String ten);

    boolean deleteByName(String ten);

    boolean save(CanBo canBo);

    boolean existByName(String hoten);
}
