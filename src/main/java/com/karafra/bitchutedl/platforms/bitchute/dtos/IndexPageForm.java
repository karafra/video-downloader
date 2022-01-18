package com.karafra.bitchutedl.platforms.bitchute.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO of form in login page.
 * 
 * @author Karafra
 * 
 * @since 1.0
 * 
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexPageForm {
    /**
     * Link to video to parse.
     */
    private String url;
}
