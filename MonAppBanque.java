import javax.swing.*; // Pour fabriquer la fenêtre et les boutons
import java.awt.*;    // Pour la disposition (Layout) et la mise en page
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ========================================================
// 1. LOGIQUE POO (Notre Compte Bancaire)
// ========================================================
class CompteBancaire {
    private double solde;

    public CompteBancaire(double soldeInitial) {
        this.solde = soldeInitial;
    }

    public double getSolde() {
        return this.solde;
    }

    public void deposer(double montant) {
        if (montant > 0) {
            this.solde += montant;
        }
    }

    public boolean retirer(double montant) {
        if (montant > 0 && montant <= this.solde) {
            this.solde -= montant;
            return true;
        }
        return false;
    }
}

// ========================================================
// 2. INTERFACE GRAPHIQUE (La Fenêtre)
// ========================================================
public class MonAppBanque {

    public static void main(String[] args) {
        
        // A. Création de notre objet Compte avec 100 € de départ
        CompteBancaire monCompte = new CompteBancaire(100.0);

        // B. Création de la Fenêtre principale (JFrame)
        JFrame fenetre = new JFrame("Ma Banque Java 🏦");
        fenetre.setSize(400, 300); // Largeur: 400px, Hauteur: 300px
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer l'appli quand on clique sur X
        fenetre.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15)); // Organisation simple des éléments

        // C. Création des composants visuels (Labels, Champ texte, Boutons)
        JLabel labelTitre = new JLabel("=== GESTION DE COMPTE ===");
        
        // Texte affichant le solde en direct
        JLabel labelSolde = new JLabel("Solde actuel : " + monCompte.getSolde() + " €");
        labelSolde.setFont(new Font("Arial", Font.BOLD, 16)); // Rendre le texte plus grand et en gras

        JLabel labelInstruction = new JLabel("Entrez un montant :");
        
        // Zone pour écrire le montant (largeur de 15 caractères)
        JTextField champMontant = new JTextField(15);

        // Deux boutons cliquables
        JButton boutonDeposer = new JButton(" Déposer ➕ ");
        JButton boutonRetirer = new JButton(" Retirer ➖ ");

        // D. Ajout de tous les composants dans la fenêtre (ordre d'affichage)
        fenetre.add(labelTitre);
        fenetre.add(labelSolde);
        fenetre.add(labelInstruction);
        fenetre.add(champMontant);
        fenetre.add(boutonDeposer);
        fenetre.add(boutonRetirer);

        // ========================================================
        // 3. LES ACTIONS QUAND ON CLIQUE SUR LES BOUTONS
        // ========================================================

        // Action lors du clic sur "Déposer"
        boutonDeposer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // 1. On lit ce que l'utilisateur a tapé dans le champ texte
                    String texteEntre = champMontant.getText();
                    double montant = Double.parseDouble(texteEntre); // Conversion texte -> nombre

                    // 2. On fait le dépôt en POO
                    monCompte.deposer(montant);

                    // 3. On met à jour l'affichage du solde à l'écran
                    labelSolde.setText("Solde actuel : " + monCompte.getSolde() + " €");
                    champMontant.setText(""); // Vider le champ texte
                    
                } catch (NumberFormatException ex) {
                    // Si l'utilisateur tape des lettres au lieu d'un nombre
                    JOptionPane.showMessageDialog(fenetre, "Erreur : Veuillez entrer un nombre valide !");
                }
            }
        });

        // Action lors du clic sur "Retirer"
        boutonRetirer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String texteEntre = champMontant.getText();
                    double montant = Double.parseDouble(texteEntre);

                    // Tentative de retrait
                    boolean succes = monCompte.retirer(montant);

                    if (succes) {
                        labelSolde.setText("Solde actuel : " + monCompte.getSolde() + " €");
                        champMontant.setText("");
                    } else {
                        JOptionPane.showMessageDialog(fenetre, "Retrait impossible : Solde insuffisant !");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(fenetre, "Erreur : Veuillez entrer un nombre valide !");
                }
            }
        });

        // E. Afficher la fenêtre à l'écran !
        fenetre.setLocationRelativeTo(null); // Centrer sur l'écran
        fenetre.setVisible(true); // Rendre visible !
    }
}