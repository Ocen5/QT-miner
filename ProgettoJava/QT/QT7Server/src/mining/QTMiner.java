package mining;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import data.Data;

public class QTMiner implements Serializable {

	private ClusterSet C;
	private double radius;

	public QTMiner(double radius) {
		C = new ClusterSet(); // Crea l'oggetto array riferito da C
		this.radius = radius; // inizializza radius con il parametro passato in input
	}

	public QTMiner(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {

		FileInputStream inf = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(inf); // Apre il file identificato da fileName
		C = (ClusterSet) in.readObject(); // legge e assegna l'oggetto memorizzato a C.
		in.close();
	}

	public void salva(String fileName) throws FileNotFoundException, IOException {
		
		FileOutputStream outf = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(outf); // Apre il file identificato fileName
		out.writeObject(C); // salva l'oggetto C nel file.
		out.close();
	}

	public ClusterSet getC() {
		return C;// restituisce l'insieme dei cluster C
	}

	/* Costruisce un cluster per ciascuna tupla non ancora clusterizzata, includendo
	 * nel cluster i punti (non ancora clusterizzati in alcun altro cluster) che
	 * ricadono nel vicinato sferico delle tuple con raggio radius
	 */
	public int compute(Data data) throws ClusteringRadiusException {
		int numclusters = 0;
		boolean isClustered[] = new boolean[data.getNumberOfExamples()];

		for (int i = 0; i < isClustered.length; i++)
			isClustered[i] = false;
		int countClustered = 0;

		while (countClustered != data.getNumberOfExamples()) {
			// Ricerca e salvataggio del cluster più popoloso
			Cluster c = buildCandidateCluster(data, isClustered);
			C.add(c);
			numclusters++;

			// Rimuovo tutti i punti di tale cluster dall'elenco delle tuple da clusterizzare

			for (Integer it : c) {
				isClustered[it] = true;
			}
			countClustered += c.getSize();
			// Ripete il while finchè ci sono ancora tuple da assegnare ad un cluster

		}

		if (numclusters == 1) {
			throw new ClusteringRadiusException();
		} else {
			return numclusters;
		}

	}

	/*
	 * insieme di tuple da raggruppare in cluster,se isClustered[i]=FALSE allora la
	 * tupla i-esima di data non è stata assegnata ad alcun cluster di C, TRUE
	 * altrimenti
	 */
	Cluster buildCandidateCluster(Data data, boolean isClustered[]) { // cD cluster vuoto che conterrà il più popoloso
		Cluster cD = null;
		for (int i = 0; i < isClustered.length; i++) {
			// inizializza il cluster candidato C con tutte le tuple che rientrano in radius
			Cluster C = new Cluster(data.getItemSet(i));
			if (isClustered[i] == false) {
				for (int j = 0; j < isClustered.length; j++) {
					if (isClustered[j] == false) {
						if (data.getItemSet(i).getDistance(data.getItemSet(j)) <= radius) {
							C.addData(j);
						}
					}
				}

				if (cD == null)
					cD = C;
				else if (C.getSize() > cD.getSize())// qui decido quale cluster tra cC e C è più popoloso
					cD = C;
			}
		}
		return cD;
		
		/* costruisce un cluster per ciascuna tupla di data non ancora clusterizzata in
		 * un cluster di C e restituisce il cluster candidato più popoloso
		 */
	}
	public String toString()
	{
		return C.toString();
	}
}
