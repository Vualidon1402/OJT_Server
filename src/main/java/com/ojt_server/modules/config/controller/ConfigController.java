package com.ojt_server.modules.config.controller;

import com.ojt_server.modules.config.ConfigModel;
import com.ojt_server.modules.config.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private ConfigService configService;

    @GetMapping("/findAll")
    public List<ConfigModel> getAllConfig() {
        return configService.findAllConfig();
    }

    // Add new config
    @PostMapping("/add")
    public ConfigModel addConfig(@RequestBody ConfigModel config){
        return configService.addConfig(config);
    }

    // Update config
    @PutMapping("/update")
    public ConfigModel updateConfig (@RequestBody ConfigModel config){
        return configService.updateConfig(config);
    }

    // Update config status instead of deleting
    @DeleteMapping("/delete/{id}")
    public void deleteConfig(@PathVariable Long id){
        configService.deleteConfig(id);
    }
}
