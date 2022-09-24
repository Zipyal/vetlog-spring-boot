/*
Copyright 2022 José Luis De la Cruz Morales joseluis.delacruz@gmail.com
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.jos.dem.vetlog.command;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PetLogCommand implements Command {
    @Size(max = 200)
    private String vetName;

    @NotNull
    @Size(min = 1, max = 1000)
    private String signs;

    @NotNull
    @Size(min = 1, max = 1000)
    private String diagnosis;

    @Size(min = 1, max = 1000)
    private String medicine;

    @NotNull
    @Min(1L)
    private Long pet;

    private MultipartFile attachment;
}
