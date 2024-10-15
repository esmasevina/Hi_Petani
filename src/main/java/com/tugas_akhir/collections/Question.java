package com.tugas_akhir.collections;

public class Question {
	public String	body,
					judul,
					author,
					kategori [],
					fotoAuthor;
	
	public int idPertanyaan,like;
	
	
	// constructor
	public Question(int like, String body, String judul, String author, 
					String fotoAuthor,int idPertanyaan) {
		this.like = like;
		this.body = body;
		this.judul = judul;
		this.author = author;
		this.fotoAuthor = fotoAuthor;
		this.idPertanyaan = idPertanyaan;
	}
	
	public void addKategori(String ...kategori) {
		if(this.kategori == null) {
			this.kategori = kategori;
		}else {
			String[] result;
	        result = new String[this.kategori.length+1];
	       

	        for(int i = 0;i< result.length ;i++){
	            try{
	                result[i] = this.kategori[i]; 
	            }catch(Exception e){
	            	result[i] = kategori[0];
	            }
	        }
	        this.kategori = result;
		}
	}


	@Override
	public String toString() {
		return "Question [like=" + like + ", body=" + body + ", judul=" + judul + ", author=" + author + ", kategori="
				+ kategori + ", fotoAuthor=" + fotoAuthor + ", idPertanyaan=" + idPertanyaan + "]";
	}
	
	
	
	
					
	
	
}
