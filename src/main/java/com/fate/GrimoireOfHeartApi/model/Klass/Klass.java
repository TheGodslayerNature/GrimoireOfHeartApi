package com.fate.GrimoireOfHeartApi.model.Klass;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface Klass extends Serializable {
    String getNomeDaClasse();
    List<String> getHabilidades();
}
