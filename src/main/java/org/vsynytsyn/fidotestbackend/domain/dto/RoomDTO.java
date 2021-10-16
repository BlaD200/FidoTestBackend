package org.vsynytsyn.fidotestbackend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    @NotNull
    @NotBlank
    @Size(max = 50, message = "Room name length must be less than 50")
    private String name;
    @Size(max = 256, message = "Room location length must be less than 256")
    private String location;
    @NotNull
    @NotBlank
    @Max(value = 100, message = "Seats count can be 100 max")
    private Integer seatsCount;
}
