package com.fate.GrimoireOfHeartApi.model.atributo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Data
@Setter
@Getter
public class AtributosDeBatalha implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Long forca =  0L;
    private Long vitalidade =  0L;
    private Long magia =  0L;
    private Long tecnica =  0L;
    private Long agilidade =  0L;
    private Long sorte =  0L;
    public String printAtributosDeBatalha(){
        return String.format("FOR: %o, TEC: %o, VIT: %o, MAG: %o, AGI: %o, SOR: %o", forca,tecnica,vitalidade,magia,agilidade,sorte);
    }

}
