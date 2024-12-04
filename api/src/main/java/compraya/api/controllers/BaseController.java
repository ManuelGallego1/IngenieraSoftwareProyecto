package compraya.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import compraya.api.interfaces.ICrudService;

public abstract class BaseController<T> {

    protected abstract ICrudService<T> getService();

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAll() {
        return getService().get();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody T entity) {
        return getService().post(entity);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return getService().getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody T entity, @PathVariable("id") Long id) {
        return getService().put(entity, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return getService().delete(id);
    }
}