package com.asshofa.management.model.pojo;

import com.asshofa.management.util.EncryptionUtil;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReferensiSpesialisasiPojo {
    private String id;
    private Short idALong;
    private String spesialisasi;

    public String getId(Short idALong) {
        return idALong != null ? EncryptionUtil.encrypt(idALong) : null;
    }

    public void setId(String id) {
        this.id = id;
        this.idALong = id != null ? EncryptionUtil.decrypt(id) : null;
    }

}
