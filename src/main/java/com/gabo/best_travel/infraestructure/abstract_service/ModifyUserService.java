package com.gabo.best_travel.infraestructure.abstract_service;

import java.util.List;
import java.util.Map;

public interface ModifyUserService {
    Map<String, Boolean> enabled(String username);
    Map<String, List<String>> addRole(String username, String role);
    Map<String, List<String>> removeeRole(String username, String role);
}
