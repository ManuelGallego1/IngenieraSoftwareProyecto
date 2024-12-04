package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;

public interface ICrudService<T> {
    ResponseEntity<?> get();
    ResponseEntity<?> post(T entity);
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> put(T entity, Long id);
    ResponseEntity<?> delete(Long id);
}