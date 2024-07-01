package poiji;


import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PTest {

    public static void main(String[] args) {

        System.out.println(getList(SmokeTestData.class,"./src/test/ExcelTestData.xlsx"));
        System.out.println("\n");
        List<RegressionTestData> lst =  getList(RegressionTestData.class,"./src/test/ExcelTestData.xlsx");
        lst.stream().forEach(item-> System.out.println(item));
        Map<String,String> testMap=lst.stream().collect(Collectors.toMap(RegressionTestData::getPassword,RegressionTestData::getUserName));
        System.out.println(testMap.values());

    }

    public static <T> List<T> getList(Class<T> cls, String filePath){
        PoijiOptions options = PoijiOptions.PoijiOptionsBuilder.settings(1).build();
        List<T> dataList = Poiji.fromExcel(new File(filePath),cls);
        return dataList;
    }
}
