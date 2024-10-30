package miPrimerSpring.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/apiarchivos")
public class SaludoController {

    // Endpoint para obtener una imagen por nombre
    @GetMapping("/imagen/{nombre}")
    public ResponseEntity<Resource> obtenerImagen(@PathVariable String nombre) throws IOException {
        return obtenerRecurso("imagenes/" + nombre + ".jpg", MediaType.IMAGE_JPEG);
    }

    // Endpoint para obtener un archivo HTML por nombre
    @GetMapping("/html/{nombre}")
    public ResponseEntity<Resource> obtenerHtml(@PathVariable String nombre) throws IOException {
        return obtenerRecurso("html/" + nombre + ".html", MediaType.TEXT_HTML);
    }

    // Endpoint para obtener un archivo XML por nombre
    @GetMapping("/xml/{nombre}")
    public ResponseEntity<Resource> obtenerXml(@PathVariable String nombre) throws IOException {
        return obtenerRecurso("xml/" + nombre + ".xml", MediaType.APPLICATION_XML);
    }

    // Endpoint para obtener un archivo JSON por nombre
    @GetMapping("/json/{nombre}")
    public ResponseEntity<Resource> obtenerJson(@PathVariable String nombre) throws IOException {
        return obtenerRecurso("json/" + nombre + ".json", MediaType.APPLICATION_JSON);
    }

    // Endpoint para obtener un archivo PDF por nombre
    @GetMapping("/pdf/{nombre}")
    public ResponseEntity<Resource> obtenerPdf(@PathVariable String nombre) throws IOException {
        return obtenerRecurso("pdf/" + nombre + ".pdf", MediaType.APPLICATION_PDF);
    }

    // Método general para obtener cualquier recurso estático
    private ResponseEntity<Resource> obtenerRecurso(String ruta, MediaType mediaType) throws IOException {
        Resource recurso = new ClassPathResource("static/" + ruta);
        if (recurso.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(mediaType);
            return new ResponseEntity<>(recurso, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
