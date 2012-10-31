package gov.nih.nci.cabig.caaers.domain;

import gov.nih.nci.cabig.ctms.domain.AbstractMutableDomainObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.io.FileUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This class represents the documents associated with {@link AdditionalInformation }
 *
 * @author Saurabh Agrawal
 * @since 10/25/2012
 */
@Entity
@Table(name = "additional_info_document")
@GenericGenerator(name = "id-generator", strategy = "native", parameters = {@Parameter(name = "sequence", value = "seq_additional_info_documen_id")})
public class AdditionalInformationDocument extends AbstractMutableDomainObject implements Comparable<AdditionalInformationDocument> {

    private String fileId;
    private String fileName;
    private String originalFileName;
    private String filePath;
    private Long fileSize;
    private String relativePath;

    /**
     * The document type
     */
    private AdditionalInformationDocumentType additionalInformationDocumentType;


    /**
     * The additional information
     */
    private AdditionalInformation additionalInformation;


    @Column(name = "file_id")
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "file_path")
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Transient
    public String getByteCountToDisplaySize() {
        return FileUtils.byteCountToDisplaySize(fileSize);
    }

    @Column(name = "file_size")
    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }


    @Column(name = "relative_path")
    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }


    @Column(name = "original_file_name")
    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    /**
     * Gets the additionalInformationDocumentType
     *
     * @return the additionalInformationDocumentType
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    public AdditionalInformationDocumentType getAdditionalInformationDocumentType() {
        return additionalInformationDocumentType;
    }

    /**
     * Sets the additionalInformationDocumentType.
     *
     * @param additionalInformationDocumentType
     *         - the new additionalInformationDocumentType
     */
    public void setAdditionalInformationDocumentType(AdditionalInformationDocumentType additionalInformationDocumentType) {
        this.additionalInformationDocumentType = additionalInformationDocumentType;
    }

    @ManyToOne
    @JoinColumn(name = "additional_information_id")
    public AdditionalInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(AdditionalInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
    }


    /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
        return result;
    }

    /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AdditionalInformationDocument other = (AdditionalInformationDocument) obj;
        if (fileId == null) {
            if (other.fileId != null)
                return false;
        } else if (!fileId.equals(other.fileId))
            return false;
        return true;
    }

    public int compareTo(AdditionalInformationDocument obj) {
        return this.fileName.compareTo(obj.fileName);
    }


    @Override
    public String toString() {
        return "AdditionalInformationDocument{" +
                "fileId='" + fileId + '\'' +
                ", originalFileName='" + originalFileName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", byteCountToDisplaySize=" + getByteCountToDisplaySize() +
                ", relativePath='" + relativePath + '\'' +
                ", additionalInformationDocumentType=" + additionalInformationDocumentType +
                '}';
    }

    @Transient
    public File getFile() {
        return new File(getFilePath());
    }

    public static Map<String, List<AdditionalInformationDocument>> groupDocumentsByDocumentType(List<AdditionalInformationDocument> additionalInformationDocuments) {
        Map<String, List<AdditionalInformationDocument>> documents = new HashMap<String, List<AdditionalInformationDocument>>();


        for (final AdditionalInformationDocumentType documentType : AdditionalInformationDocumentType.values()) {
            List<AdditionalInformationDocument> select = new ArrayList<AdditionalInformationDocument>();

            CollectionUtils.select(additionalInformationDocuments, new Predicate() {
                public boolean evaluate(Object o) {
                    AdditionalInformationDocument additionalInformationDocument = (AdditionalInformationDocument) o;
                    return additionalInformationDocument.getAdditionalInformationDocumentType().equals(documentType);
                }
            }, select);

            if (!select.isEmpty()) {
                documents.put(String.format("%s", documentType.getCode()), select);
            }
        }

        return documents;
    }
}
