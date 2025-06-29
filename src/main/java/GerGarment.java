//André Luiz Gonçalves da Silva Teixeira
//RA: 2564289

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author andre
 */

import java.util.ArrayList;
import java.util.List;

public class GerGarment {

    private final List<Garment> garmentList;
    private int nextId = 1;

    private static GerGarment gerGarment;
    
    //Singleton
    private GerGarment() {
        garmentList = new ArrayList<>();
    }

    public static GerGarment getGerGarment() {
        if (gerGarment == null) {
            gerGarment = new GerGarment();
        }
        return gerGarment;
    }

    public List<Garment> getGarmentList() {
        return garmentList;
    }

    public void insertGarment(Garment garment) {
        garment.setId(nextId++);
        garmentList.add(garment);
    }

    public Garment consultGarmentById(int id) {
        return garmentList.stream()
                .filter(garment -> garment.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean updateGarment(Garment updatedGarment) {
        for (int i = 0; i < garmentList.size(); i++) {
            if (garmentList.get(i).getId() == updatedGarment.getId()) {
                garmentList.set(i, updatedGarment);
                return true;
            }
        }
        return false;
    }

    public boolean deleteGarment(int id) {
        Garment garmentToRemove = consultGarmentById(id);
        if (garmentToRemove != null) {
            garmentList.remove(garmentToRemove);
            return true;
        }
        return false;
    }
}
