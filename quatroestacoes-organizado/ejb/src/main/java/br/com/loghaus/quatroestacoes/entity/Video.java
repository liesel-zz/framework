package br.com.loghaus.quatroestacoes.entity;

import br.com.supero.framework.base.entity.impl.LogBaseEntityImpl;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "video")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "VIDEO.FIND_ALL", query = "SELECT c FROM Video c")
})
public class Video extends LogBaseEntityImpl<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "url", length = 500)
    private String url;



    @NotEmpty
    @Column(name = "thumbnail", length = 500)
    private String thumbNail;

    // corre'c~oes para o player de video em html 5
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private VideoType videoType;

    @NotEmpty
    @Size(min = 3, max = 100)
    private String nome;

    @NotEmpty
    @Size(min = 3, max = 500)
    private String descricao;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)

    @JoinTable(name = "VIDEO_TAG", schema = "SITEDASPRIMAS",
            joinColumns = {@JoinColumn(name = "id_video", referencedColumnName = "id", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "id_tag", referencedColumnName = "id", nullable = false, updatable = false)})
    private Set<Tag> listTags;

    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Tag> getListTags() {
        return listTags;
    }

    public void setListTags(Set<Tag> listTags) {
        this.listTags = listTags;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Video other = (Video) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
