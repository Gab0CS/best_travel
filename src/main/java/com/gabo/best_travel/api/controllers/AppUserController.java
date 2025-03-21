package com.gabo.best_travel.api.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabo.best_travel.infraestructure.abstract_service.ModifyUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "user")
@AllArgsConstructor
@Tag(name = "User")
public class AppUserController {
    private final ModifyUserService modifyUserService;

    @Operation(summary = "Enable or disable user")
    @PatchMapping(path = "enabled-or-disabled")
    public ResponseEntity<Map<String, Boolean>> enableOrDisabled(@RequestParam String username){
        return ResponseEntity.ok(this.modifyUserService.enabled(username));
    }

    @Operation(summary = "Add role to user")
    @PatchMapping(path = "add-role")
    public ResponseEntity<Map<String, Set<String>>> addRole(@RequestParam String username, @RequestParam String role){
        return ResponseEntity.ok(this.modifyUserService.addRole(username, role));
    }

    @Operation(summary = "Enable or disable user")
    @PatchMapping(path = "remove-role")
    public ResponseEntity<Map<String, Set<String>>> removeRole(@RequestParam String username, @RequestParam String role){
        return ResponseEntity.ok(this.modifyUserService.removeRole(username, role));
    }
}
 