package com.ojt_server.modules.config.service;

import com.ojt_server.modules.config.ConfigModel;
import com.ojt_server.modules.config.repository.ConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigService {
    @Autowired
    private ConfigRepository configRepository;

    //View all configs
    public List<ConfigModel> findAllConfig() {
        return configRepository.findAll();
    }

    //add config
    public ConfigModel addConfig(ConfigModel Config) {
        try {
            return configRepository.save(Config);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update config
    public ConfigModel updateConfig(ConfigModel config) {
        try {
            return configRepository.save(config);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update category status instead of deleting
    public void deleteConfig(Long id) {
        try {
            configRepository.findById(id).ifPresent(config -> {
                config.setStatus(false);
                configRepository.save(config);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
