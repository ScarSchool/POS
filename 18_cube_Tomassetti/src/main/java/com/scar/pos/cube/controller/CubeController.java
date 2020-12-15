package com.scar.pos.cube.controller;

import com.scar.pos.cube.data.DAO;
import com.scar.pos.cube.model.Cube;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CubeController {
    private static DAO dao = DAO.getInstance();

    @RequestMapping("/cubes")
    public List<Cube> getAllBottles() {
        return dao.getAll();
    }
}