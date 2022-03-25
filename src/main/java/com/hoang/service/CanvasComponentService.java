package com.hoang.service;

import com.hoang.component_classes.CanvasComponent;
import com.hoang.repository.CanvasComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CanvasComponentService {
    @Autowired
    private CanvasComponentRepository repository;

    public void save(CanvasComponent canvas) {
        repository.save(canvas);
    }

    public boolean isEmptyCanvasInHistory() {
        List<CanvasComponent> canvases = repository.findAll();

        return (canvases.size() == 0);
    }

    public void deleteCanvasComponentByDateCreatedAfter(Date date) {
        repository.deleteCanvasComponentByDateCreatedAfter(date);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public CanvasComponent getLastCanvasComponent() {
        List<CanvasComponent> canvases = repository.findAll();
        CanvasComponent lastCanvas = new CanvasComponent(1, 1);
        if(canvases.size() > 0) {
            lastCanvas = canvases.get(canvases.size() - 1);
        }

        return lastCanvas;
    }
}
