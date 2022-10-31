package tickets.loader;

import java.io.IOException;

public interface Importable {

    Object[] importDataFromFile() throws IOException;
}
