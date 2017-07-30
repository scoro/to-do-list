package pl.pollub.coWorker.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateCoWorker {
    private final int id;

    private String name;
}
