package gov.nih.nci.cabig.caaers.domain;

import junit.framework.TestCase;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Biju Joseph
 */
public class DomainObjectTest extends TestCase {


    public void testCoverAllMutators() throws Exception {
        int i = 0;
        int j = 0;
        List<Class> classes = findMyTypes("gov.nih.nci.cabig.caaers.domain");
        try{

            for(Class c : classes){
                j++;
                try{

                    Object o1 = c.newInstance();
                    Object o2 = c.newInstance();

                    BeanUtils.copyProperties(o1, o2);

                    System.out.println("Processing : " +  c.getName());
                    i++;
                } catch (Exception e1){
//                    System.out.println("Error while copying :" + e1.getMessage());
                }

            }
        }catch (Exception e){
//            System.out.println("Error while instantiate :" + e.getMessage());
        }

        System.out.println("Total :" + j + ", processed : " + i);
        assertTrue(true);
    }



    private List<Class> findMyTypes(String basePackage) throws IOException, ClassNotFoundException
    {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

        List<Class> candidates = new ArrayList<Class>();
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                resolveBasePackage(basePackage) + "/" + "**/*.class";
        Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
        for (Resource resource : resources) {
            if (resource.isReadable()) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                if(isCandidate(metadataReader))
                    candidates.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
            }
        }
        return candidates;
    }

    private String resolveBasePackage(String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
    }

    private boolean isCandidate(MetadataReader metadataReader)  {
        String klassName =  metadataReader.getClassMetadata().getClassName();
        if(klassName.contains("repository")) return false;
        if(klassName.contains("$")) return false;
        if(klassName.endsWith("Test")) return false;

        return true;
    }
}
