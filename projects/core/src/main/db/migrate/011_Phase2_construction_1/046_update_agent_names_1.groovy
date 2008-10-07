class UpdateAgentNames extends edu.northwestern.bioinformatics.bering.Migration {
	void up() {
		
			execute("update agents set name =  '5å«-O-Benzoyl-2,3å«-anhydrothymidine' 											 where nsc ='733564'");
			execute("update agents set name =  'AFP464 (aminoflavone prodrug)'  												 where nsc ='710464'");
			execute("update agents set name =  'AZD2171 (cediranib, Recentin)'  												 where nsc ='732208'");	
			execute("update agents set name =  'CAT-5001 (SS1(dsFv) PE38)' 													     where nsc ='726388'");	
			execute("update agents set name =  'Cetuximab  (Erbitux)'															 where nsc ='714692'");
			execute("update agents set name =  'DMS612 (Dimethane sulfonate-aldehyde degradation product)'                       where nsc ='281612'");	
			execute("update agents set name =  'Erythropoietin, recombinant human (Epoetin alfa, Procrit - Ortho Biotech)'       where nsc ='628281'");	
			execute("update agents set name =  'Everolimus (RAD-001)'                                                            where nsc ='733504'");	
			execute("update agents set name =  'IMC-A12 (HuMAb IGF-1R)'							     						     where nsc ='742460'");	
			execute("update agents set name =  'Imexon (Amplimexon)'                                                             where nsc ='714597'");
			
			insert('agents', [ id: -656, nsc: 111111, name: "Ipilimumab (MDX-010; Transfectoma-derived)"], primaryKey: false)
			execute("update agents set name =  (select name from agents where id=-656)  where nsc ='732442'");
			execute("delete from agents where id=-656")	
			
			execute("update agents set name =  'LMP776'                                                                          where nsc ='725776'");	
			execute("update agents set name =  'MS-275 (SNDX-275, entinostat)'                                                   where nsc ='706995'");	
			execute("update agents set name =  'Nelarabine (506U78)'                                                             where nsc ='686673'");	
			execute("update agents set name =  'PF-03512676 (CpG ODN 7909)'                                                      where nsc ='731581'");	
			execute("update agents set name =  'PXD 101 (belinostat)'                                                            where nsc ='726630'");	
			execute("update agents set name =  'QS-21'                                                                           where nsc ='679689'");	
			execute("update agents set name =  'R406 (Rigel)'                                                                    where nsc ='742317'");	
			execute("update agents set name =  'Rapamycin (sirolimus, Rapamune)'                                                 where nsc ='226080'");	
			
			insert('agents', [ id: -434, nsc: 111111, name: "Romidepsin (Depsipeptide; FK228)"], primaryKey: false)
			execute("update agents set name =  (select name from agents where id=-434)  where nsc ='630176'");
			execute("delete from agents where id=-434")	

			execute("update agents set name =  'VEGF-Trap (aflibercept)'                                                         where nsc ='724770'");

	}
	
	void down(){
		
	}
}