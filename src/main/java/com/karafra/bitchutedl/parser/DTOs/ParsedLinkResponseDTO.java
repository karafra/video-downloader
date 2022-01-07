package com.karafra.bitchutedl.parser.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParsedLinkResponseDTO {
  private String linkRaw;
  private String linkOriginal;
}
