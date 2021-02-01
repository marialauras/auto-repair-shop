package classes;

import java.util.ArrayList;

public class List<T> {

	private ArrayList<T> list;
	
	//Construtores
	public List() {
        this.list = new ArrayList<>();
    }

    public List(ArrayList<T> list) {
        this.list = new ArrayList<>(list);
    }

	//Getters
	public ArrayList<T> getList() {
		return list;
	}

	public T get(int index) {
		if(index >= 0 && index < this.list.size())
			return list.get(index);
		return null;
	}
	
	//Setters
	public boolean set(T item, int index) {
		if (index >= 0 && index < this.list.size()) {
			list.set(index, item);
			return true;
		}
		return false;
	}
	
	//Adicionam e removem itens
	public boolean add(T item){
		return this.list.add(item);
	}
	
	public boolean delete(int index) {
		if (index >= 0 && index < this.list.size()) {
			list.remove(index);
			return true;
		}
		return false;
	}
	
	//Retorna o tamanho do array
	public int tamanho(){
		return this.list.size();
	}
	//Salva os itens do array em um vetor de strings
	public String[] toList() {
		String[] out = new String[this.list.size()];

		for (int i = 0; i < this.list.size(); i++)
			out[i] = this.list.get(i).toString();

		return out;
	}
}