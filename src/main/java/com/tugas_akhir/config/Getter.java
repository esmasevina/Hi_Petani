package com.tugas_akhir.config;
import com.tugas_akhir.collections.Comment;
import com.tugas_akhir.collections.Question;
import com.tugas_akhir.collections.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;



public class Getter {
	private static Connection conn = null;
	private static ResultSet resultData = null;
	
	
	// test, jika koneksi database tidak berhasil maka langsung exit (Aplikasi berhenti).
	static {
		
		try {
			// jalankan koneksi ke database
			conn = Config.connection();
			if(!conn.isClosed()){
				System.out.println("Koneksi Database Berhasil : Getter.class");
			}
			
			conn.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.exit(0);
			}
		}
	
	// method untuk mengambil data semua member
	public static ArrayList<User> getAllMember(){
		// buat instance untuk penyimpanan sementara
		ArrayList<User> member = new ArrayList<User>();

		try {
			// jalankan koneksi ke database
			conn = Config.connection();
			// eksekusi query return nya disimpan ke resultData
			resultData = conn.createStatement().executeQuery("SELECT * FROM `member`");
			while (resultData.next()) {
				User data = new User();
				data.username = resultData.getString("username");
				data.namaLengkap = resultData.getString("nama");
				data.noHP = resultData.getString("noHp") ;
				data.setId(Integer.parseInt(resultData.getString("id")));
				data.password = resultData.getString("password");
				member.add(data);
			}
			// tutup conn
			conn.close();
			
			// kirim nilai
			return member;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public static Question getQuestion(int idPertanyaan){
		ArrayList<Question> rawData = getAllQuestion();

		for (Question data:rawData) {
			if(data.idPertanyaan == idPertanyaan)
				return data;
		}

		return null;
	}

	public static ArrayList<Comment> getComments(int idPertanyaan){
		String query = "SELECT `pertanyaan`.`id` AS `idPertanyaan`, `member`.`username` AS `namaKomentator`,`komentar`.`isi` AS `isiKomentar` FROM " +
				"`komentar` JOIN `pertanyaan` ON `komentar`.`pertanyaan` = `pertanyaan`.`id`" +
				"JOIN `member` ON `komentar`.`komentator` = `member`.`id`";
		ArrayList<Comment> rawData = new ArrayList<>();

		try {
			// jalankan koneksi ke database
			conn = Config.connection();
			// eksekusi query return nya disimpan ke resultData
			resultData = conn.createStatement().executeQuery(query);
			while (resultData.next()) {
				Comment komentar = new Comment(Integer.valueOf(resultData.getString("idPertanyaan")),
												resultData.getString("namaKomentator"),
												resultData.getString("isiKomentar"));
				rawData.add(komentar);
			}
			// tutup conn
			conn.close();
			ArrayList<Comment> result = new ArrayList<>();

			try{
				// seleksi hanya komentar yang berhubungan dengan idPertanyaan
				for (Comment data: rawData) {
					if(data.idPertanyaan == idPertanyaan){
						result.add(data);
					}
				}
			}catch (Exception ex){
				ex.printStackTrace();
				return null;
			}

			// kirim data yang sudah diseleksi
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}


		return null;
	}

	
	// method untuk mengambil data semua pertanyaan
	public static ArrayList<Question> getAllQuestion(){
		// buat instance untuk penyimpanan sementara
		ArrayList<Question> pertanyaan = new ArrayList<Question>();
 				
		try {
			// jalankan koneksi ke database
			conn = Config.connection();
			// eksekusi query return nya disimpan ke resultData
			resultData = conn.createStatement().executeQuery("SELECT `pertanyaan`.`judul` AS `judul`, `pertanyaan`.`body` AS `body`, `pertanyaan`.`like` AS `like`,"+
																	"`pertanyaan`.`id` AS `id`,"+
																	"`member`.`username` AS `namaAuthor`, `member`.`foto` AS `foto` , `kategori`.`name` AS `kategori`"+
																	"FROM `pertanyaan` JOIN `member` ON `pertanyaan`.`author` = `member`.`id` "+
																	"JOIN `kontrakKategori` ON `kontrakKategori`.`pertanyaan` = `pertanyaan`.`id` "+
			 /*pemindahan data*/									"JOIN `kategori` ON `kategori`.`id` = `kontrakKategori`.`kategori`");
			int a = 0;
			while (resultData.next()){
				String judul =resultData.getString("judul");
				String body = resultData.getString("body");
				String nama = resultData.getString("namaAuthor");
				String foto = resultData.getString("foto");
				int like = Integer.parseInt(resultData.getString("like")) ;	
				String kategori = resultData.getString("kategori");	
				int id = Integer.parseInt(resultData.getString("id"));
				
		
				Question data = new Question(like, body, judul, nama, foto, id);
				data.addKategori(kategori);
				
				// penghapusan redudance
				if(pertanyaan.size() != 0) {
					if(pertanyaan.get(a).idPertanyaan != data.idPertanyaan) {
						pertanyaan.add(data);
						a++;
					}else {
						pertanyaan.get(a).addKategori(data.kategori[0]);
					}	
				}else if(pertanyaan.size() == 0) {
					pertanyaan.add(data);
				}
				
			}
			
			
			// tutup conn
			conn.close();
				
			// kirim nilai
			return pertanyaan;
		}catch(Exception e) {
			e.printStackTrace();	
		}
				
		
		return null;
	}

	public static ArrayList<Question> getTopQuestion(){

		// buat instance untuk penyimpanan sementara
		ArrayList<Question> pertanyaan = getAllQuestion();

		// sort secara descending (berdasarkan jumlah like)
		ArrayList<Question> pertanyaanDesc = sortDesc(pertanyaan);

		// seleksi 5 dari pertanyaan yang likenya paling banyak
		ArrayList<Question> result = new ArrayList<>();
		int a = 0;
		for (Question question: pertanyaanDesc) {
			if(a < 5)
				result.add(question);
			else
				break;
		}

		return result;
	}

	private static ArrayList<Question> sortDesc(ArrayList<Question> rawData){
		int size = rawData.size();
		Question temp = null;
		for(int i=0; i < size; i++){
			for(int j=1; j < (size-i); j++){
				if(rawData.get(j-1).like < rawData.get(j).like){
					temp = rawData.get(j-1);
					rawData.set((j-1),rawData.get(j));
					rawData.set(j,temp);
				}
			}
		}

		return rawData;
	}
	
	
	
	//	method untuk filterin pertanyaan berdasarkan kategori
	public static  ArrayList<Question> getAllQuestions(String kategori){
		ArrayList<Question> rawData = getAllQuestion();

		// filtering
		ArrayList<Question> result = new ArrayList<>();

		for (Question data: rawData) {
			if(data.kategori[0].equalsIgnoreCase(kategori)){
				result.add(data);
			}
		}

		return result;
	}

	
	
	// method untuk mengambil data semua Kategori
		public static String[][] getAllKategori(){
				
			// buat instance untuk penyimpanan sementara
			ArrayList<String> id = new ArrayList<>();
			ArrayList<String> nama = new ArrayList<>();
			
				
			try {
				// jalankan koneksi ke database
				conn = Config.connection();
				// eksekusi query return nya disimpan ke resultData
				resultData = conn.createStatement().executeQuery("SELECT * FROM `kategori`");
				while (resultData.next()) {
					id.add(resultData.getString("id"));
					nama.add(resultData.getString("name"));
				}
					
				// membuat array dan menginisiasi untuk return value
				String [][] result = new String[id.size()][3];
				for(int a = 0; a < id.size();a++) {
					result[a][0] = id.get(a);
					result[a][1] = nama.get(a);
				}
						
				// tutup conn
				conn.close();
					
				// kirim nilai
				return result;
			}catch(Exception e) {
				e.printStackTrace();	
			}
			return null;
		}
}
