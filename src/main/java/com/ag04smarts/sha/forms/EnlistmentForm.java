package com.ag04smarts.sha.forms;

import com.ag04smarts.sha.domain.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
public class EnlistmentForm {
    @ApiModelProperty(value = "First name of a patient", required = true)
    @NotNull(message ="{notNull.firstName}")
    @NotBlank(message = "{notBlank.firstName}")
    @Pattern(regexp = "[a-z-A-Z]*", message = "{pattern.firstName}")
    private String firstName;

    @ApiModelProperty(value = "Last name of a patient", required = true)
    @NotNull(message ="{notNull.lastName}")
    @NotBlank(message = "{notBlank.lastName}")
    @Pattern(regexp = "[a-z-A-Z]*", message = "{pattern.lastName}")
    private String lastName;

    @ApiModelProperty(value = "Email of a patient", required = true)
    @NotNull(message ="{notNull.email}")
    @NotBlank(message = "{notBlank.email}")
    @Email(message = "{email}")
    private String email;

    @ApiModelProperty(value = "Age of a patient", required = true)
    @Min(value = 0, message = "{min.age}")
    @Max(value = 120, message = "{max.age}")
    private Integer age;

    @ApiModelProperty(value = "Phone number of a patient", required = true)
    @Pattern(regexp = "[0-9]{10}", message = "{pattern.phone}")
    private String phoneNumber;

    @ApiModelProperty(value = "Gender of a patient", required = true)
    private Gender gender;
}
