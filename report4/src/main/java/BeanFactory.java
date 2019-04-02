import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

class BeanFactory {
    private int LoginServiceLevel;

    BeanFactory(String path){
        File f = new File(path);
        SAXReader sa = new SAXReader();
        Document doc = null;
        try {
            doc = sa.read(f);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootEl = doc.getRootElement();
        this.LoginServiceLevel = Integer.valueOf(rootEl.elementText("LoginServiceLevel"));
    }

    int getLoginServiceLevel(){
        return this.LoginServiceLevel;
    }

}
