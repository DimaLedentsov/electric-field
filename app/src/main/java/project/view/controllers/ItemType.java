package project.view.controllers;

public enum ItemType {
    PARTICLE("частица"),
    POTENTIONAL_LINE("эквипотенциальная линия"),
    PLANE("плоскость");
    private String label;

    ItemType(String label) {
        this.label = label;
    }
    public String toString() {
        return label;
    }
    
}
