package ap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import metric.Medidas;

public class Horario {
	public static void main(String[] args) throws ParseException {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		String nomeTabela = "GawkTabela";

		String csvPath = "C:\\Users\\Vini\\Desktop\\100%\\" + nomeTabela + ".csv";

		String metPath = "C:\\Users\\Vini\\Desktop\\100UTC\\" + nomeTabela + ".csv";

		ArrayList<String> ut = new ArrayList<String>();

		BufferedReader br = null;
		BufferedReader tr = null;
		int x = 10;
		String line = "";
		String dataInicio = "";
		String dataFim = "";
		String name = "";
		String cvsSplitBy = ";";
		String utc = "";

		try {

			br = new BufferedReader(new FileReader(csvPath));
			while ((line = br.readLine()) != null) {

				String sp = csvPath.replace("\\", "/");
				String[] rep = sp.split("/");
				String[] repo = rep[5].split("Tabela");
				name = repo[0];
				String[] coluna = line.split(cvsSplitBy);

				String[] time = coluna[9].split(" ");
				System.out.print(line.toString() + "\n");
				String date = time[0] + " " + time[1] + " " + time[2] + " " + time[3] + " " + time[4] + " " + time[5];
				Date date1 = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy").parse(date);
				//System.out.println(date1);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
				Date gmt = new Date(sdf.format(date1));
				String tempgmt = gmt.toString().replace("AMT", "UTC");
				//System.out.println(gmt);
				utc = line.replace(coluna[9], tempgmt);
				//System.out.println(utc);
				ut.add(utc + "\n");
				

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					
					e.printStackTrace();
					
				}
			}

		}
		
		String ut1 = ut.toString().replace("[" , "");
		String ut2 = ut1.toString().replace("]" , "");
		System.gc();
		String ut3 = ut2.replace(", ", "");

		try (FileWriter writer = new FileWriter(metPath); BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(ut2.toString());

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		// System.out.println(authorsVars.toString());
	}
}
