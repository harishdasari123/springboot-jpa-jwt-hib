package wissen.rest.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailEntity {

    private String from;
    private String to;
    private String subject;
    private String body;
}
