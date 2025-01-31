package com.reajason.javaweb.config;

import com.reajason.javaweb.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author ReaJason
 * @since 2024/11/24
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ShellConfig {
    @Builder.Default
    private String shellClassName = CommonUtil.generateShellClassName();
    @Builder.Default
    private String injectorClassName = CommonUtil.generateInjectorClassName();
    @Builder.Default
    private String urlPattern = "/*";
}
