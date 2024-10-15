package com.tugas_akhir.config;

import com.tugas_akhir.collections.Question;
import com.tugas_akhir.collections.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Setter {
	private static Connection conn;
	private static ResultSet resultData = null;
	private static int a;
	
	// test, jika koneksi database tidak berhasil maka langsung exit (Aplikasi berhenti).
	static {
		a = 0;
		try {
			// jalankan koneksi ke database
			conn = Config.connection();
			if(!conn.isClosed()){
				System.out.println("Koneksi Database Berhasil : Setter.class");
			}
			
			conn.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	
	
//	method menambah member
	public static int sendRegistrasi(String username,String password,String nama,String noHp) {
		a = 0;
//		checking sudah ada?
		ArrayList<User> listData = Getter.getAllMember();
		for (User data : listData) {
			if(data.username.equals(username.toLowerCase())) {
				a = -55;
				return a; 
			}
		}
		
		try {	
		// jalankan koneksi ke database
		conn = Config.connection();
												// update data (INSERT)
		 a = conn.createStatement().executeUpdate("INSERT INTO `member` (`id`, `username`, `password`, `nama`, `noHp`, `foto`) VALUES (NULL, '"+username+"', '"+password+"', '"+nama+"', '"+noHp+"', 'sadf')");
		 conn.close();
		} catch (Exception e) {
			String msg = e.getMessage();
			String subMsg = msg.substring(0, 3);
			a = subMsg.equalsIgnoreCase("you".toLowerCase())? -1 :
				subMsg.equalsIgnoreCase("tab".toLowerCase())? -2 :
				subMsg.equalsIgnoreCase("unk".toLowerCase())? -3 : 0;
			e.printStackTrace();
		}
		return a;
	}

	// method untuk merubah kategori
	public static int changeKategori(int idK,String name){
		a = 0;
		try {
			// jalankan koneksi ke database
			conn = Config.connection();

			// update data (INSERT)
			a = conn.createStatement().executeUpdate("UPDATE `kategori` SET `name`='"+name+"' WHERE `id` ="+idK );
			conn.close();
		} catch (Exception e) {
			String msg = e.getMessage();
			String subMsg = msg.substring(0, 3);
			a = subMsg.equalsIgnoreCase("you".toLowerCase())? -1 :
					subMsg.equalsIgnoreCase("tab".toLowerCase())? -2 :
							subMsg.equalsIgnoreCase("unk".toLowerCase())? -3 : 0;
			e.printStackTrace();
		}
		return a;
	}


	// method untuk menghapus kategori
	public static int removeKategori(int idK){
		a = 0;
		try {
			// jalankan koneksi ke database
			conn = Config.connection();

			// update data (INSERT)
			a = conn.createStatement().executeUpdate("DELETE FROM kategori WHERE id = "+idK );
			conn.close();
		} catch (Exception e) {
			String msg = e.getMessage();
			String subMsg = msg.substring(0, 3);
			a = subMsg.equalsIgnoreCase("you".toLowerCase())? -1 :
					subMsg.equalsIgnoreCase("tab".toLowerCase())? -2 :
							subMsg.equalsIgnoreCase("unk".toLowerCase())? -3 : 0;
			e.printStackTrace();
		}
		return a;
	}
	
	
	
	
//	Method menambah pertanyaan
	public static int sendQuestion(String judul,String body,int idUser,int kodeKategori) {
		a = 0;
		try {					   
		 // jalankan koneksi ke database
		 conn = Config.connection();
		 
		 // update data (INSERT)
		 a = conn.createStatement().executeUpdate("INSERT INTO `pertanyaan` (`id`, `author`, `judul`, `body`, `like`) VALUES (NULL, '"+idUser+"', '"+judul+"', '"+body+"', '0')");
		 kontrakKategori(kodeKategori);
		 conn.close();
		} catch (Exception e) {
			String msg = e.getMessage();
			String subMsg = msg.substring(0, 3);
			a = subMsg.equalsIgnoreCase("you".toLowerCase())? -1 :
				subMsg.equalsIgnoreCase("tab".toLowerCase())? -2 :
				subMsg.equalsIgnoreCase("unk".toLowerCase())? -3 : 0;
			e.printStackTrace();
		}
		return a;
	}
	
	
//	Method menambah kategori
	public static int sendKategori(String kategori,int idAdmin) {
		 a = 0;
//		checking sudah ada?
		String [][] daftarKategori = Getter.getAllKategori();
		for (String[] kategories : daftarKategori) {
			if(kategories[1].equalsIgnoreCase(kategori.toLowerCase())) {
				a = -55;
				return a; 
			}
		}
		try {					   
		 // jalankan koneksi ke database
		 conn = Config.connection();
		 //update data (INSERT)
		 a = conn.createStatement().executeUpdate("INSERT INTO `kategori` (`id`, `name`, `admin`) VALUES (NULL, '"+kategori.toLowerCase()+"', '"+idAdmin+"')");
		 conn.close();
		} catch (Exception e) {
			String msg = e.getMessage();
			String subMsg = msg.substring(0, 3);
			a = subMsg.equalsIgnoreCase("you".toLowerCase())? -1 :
				subMsg.equalsIgnoreCase("tab".toLowerCase())? -2 :
				subMsg.equalsIgnoreCase("unk".toLowerCase())? -3 : 0;
			e.printStackTrace();
		}
		return a;
	}

	// methode menambah like
	public static int sendLike(int idP) {
//		UPDATE `pertanyaan` SET `like` = '90' WHERE `pertanyaan`.`id` = 55
		 a = 0;
		 int like = 0;
//		checking sudah ada?
		ArrayList<Question> questions = Getter.getAllQuestion();
		for (Question question : questions) {
			if(question.idPertanyaan == idP) {
				like = question.like;
				break;
			}
		}
		like++;
		
		try {					   
		 // jalankan koneksi ke database
	 	 conn = Config.connection();
	 	 //update data (INSERT)
		 a = conn.createStatement().executeUpdate("UPDATE `pertanyaan` SET `like` = '"+like+"' WHERE `pertanyaan`.`id` = "+idP);
		 conn.close();
		} catch (Exception e) {
			String msg = e.getMessage();
			String subMsg = msg.substring(0, 3);
			a = subMsg.equalsIgnoreCase("you".toLowerCase())? -1 :
				subMsg.equalsIgnoreCase("tab".toLowerCase())? -2 :
				subMsg.equalsIgnoreCase("unk".toLowerCase())? -3 : 0;
			e.printStackTrace();
		}
		return a;
	}

	// methode menambah comments
		public static int sendComment(String isiKomen,int idPertanyaan,int idUser) {
			a = 0;
			try {
				// jalankan koneksi ke database
				conn = Config.connection();

				// update data (INSERT)
				a = conn.createStatement().executeUpdate("INSERT INTO `komentar` (`komentator`, `pertanyaan`, `isi`) VALUES ('"+idUser+"','"+idPertanyaan+"','"+isiKomen+"')");

				conn.close();
			} catch (Exception e) {
				String msg = e.getMessage();
				String subMsg = msg.substring(0, 3);
				a = subMsg.equalsIgnoreCase("you".toLowerCase())? -1 :
						subMsg.equalsIgnoreCase("tab".toLowerCase())? -2 :
								subMsg.equalsIgnoreCase("unk".toLowerCase())? -3 : 0;
				e.printStackTrace();
			}
			return a;
		}

	
	
	
	// 	=========================================== Helper =========================================
		private static int getLastIdPertanyaan() throws SQLException {
			resultData = conn.createStatement().executeQuery("SELECT `pertanyaan`.`id` FROM `pertanyaan`");
			ArrayList<Integer> id = new ArrayList<>(); 
			
			while (resultData.next()) {
				id.add(Integer.valueOf(resultData.getString("id")));
			}
			
			int result = 3;
			int index = 0;
			
			while (index < id.size()) {
				result = id.get(index);
				index++;
			}
			
			System.out.println(result);
			return result;
		}
		
		//method untuk menambahkan kontrak kategori (Antara pertanyaan dengan kategori)
		private static void kontrakKategori(int kodeKategori) throws SQLException {
			int id = getLastIdPertanyaan();

			conn.createStatement().executeUpdate("INSERT INTO `kontrakKategori` (`id`, `pertanyaan`, `kategori`) VALUES (NULL, '"+id+"', '"+kodeKategori+"')");
			
		}      
}
