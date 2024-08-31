package br.com.GerenciadorPetshop.Enum;

public enum UserRole {

    ADMIN("admin"), SALESMAN("salesman");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
