package fr.insee.onyxia.model.catalog;

import fr.insee.onyxia.model.helm.Chart;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Schema(description = "A set of packages and charts coming from a same endpoint")
public abstract class CatalogWrapper {

    @Schema(description = "This entries are those from a standard helm repository")
    private Map<String, List<Chart>> entries;

    public Optional<Chart> getPackageByName(String name) {
        return entries.get(name).stream().findFirst();
    }

    /**
     * @return the packages
     */
    public Map<String, List<Chart>> getEntries() {
        return entries;
    }

    /**
     * @param entries the packages to set
     */
    public void setEntries(Map<String, List<Chart>> entries) {
        this.entries = entries;
    }
}
