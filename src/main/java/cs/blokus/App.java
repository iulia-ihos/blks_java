package cs.blokus;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App 
{
	 /**
     * Deploys the Spring application inside an embedded Tomcat
     *
     * @param args args
     */
	
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        
      /*  System.out.println("heeeeeeeeeeeeeeeeeertttttttttttttttttttteeeeeeeeeeeeeeeeeeeeee");
        ModelMapping mm = new ModelMapping();
        TileDetails td = new TileDetails();
        td.setName(TileNameEnum.T4);
        td.setNumberSquares(2);
        
        TileSquare ts1 = new TileSquare();
        ts1.setSquare(new Square("s", 1, 1));
        ts1.setTileDetails(td);
        TileSquare ts2 = new TileSquare();
        ts2.setSquare(new Square("m", 1, 2));
        ts2.setTileDetails(td);
        List<TileSquare> lts = new ArrayList<>();
        lts.add(ts2);
        lts.add(ts1);
    
        td.setTileSquares(lts);
       
        System.out.println(td);
        Tile tile = new Tile();
        tile.setIdTile(1L);
        tile.setColor(TileColorEnum.blue);
        tile.setTileDetails(td);
        
        System.out.println(tile.toString());
        
        try {
			System.out.println(mm.convertToDto(tile, TileDTO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
       */
    }
    
   /* static ModelMapping mm;
    
    @Autowired
    static void setMm(ModelMapping m){
		App.mm = m;
    };*/
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
