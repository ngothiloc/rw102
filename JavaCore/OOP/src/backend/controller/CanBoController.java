package backend.controller;

import backend.service.ICanBoService;
import backend.service.impl.CanBoServiceImpl;
import entity.CanBo;

import java.util.List;

public class CanBoController {
    private ICanBoService canBoService;

    public CanBoController(){
        canBoService = new CanBoServiceImpl();
    }

    public List<CanBo> findAll() {
        //goi den service de lay du lieu
        return canBoService.findAll();
    }

    public List<CanBo> findByName(String ten) {
        return canBoService.findByName(ten);
    }

    public boolean deleteByName(String ten) {
        return canBoService.deleteByName(ten);
    }

    public boolean save(CanBo canBo) {
        return canBoService.save(canBo);
    }

    public boolean existByName(String hoten) {
        return canBoService.existByName(hoten);
    }
}
