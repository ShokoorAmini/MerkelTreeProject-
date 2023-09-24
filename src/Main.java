import java.util.ArrayList;
import java.util.List;

class Transaction {
    private String sender;
    private String recipient;
    private double amount;

    public Transaction(String sender, String recipient, double amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    // Getters for sender, recipient, and amount

    // Implement transaction validation if needed
}

class Block {
    private int index;
    private long timestamp;
    private List<Transaction> transactions;
    private String previousHash;
    private String hash;
    private String merkleRoot;

    public Block(int index, List<Transaction> transactions, String previousHash) {
        this.index = index;
        this.timestamp = System.currentTimeMillis();
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.merkleRoot = calculateMerkleRoot();
        this.hash = calculateHash();
    }

    private String calculateHash() {
        try {
            String dataToHash = index + timestamp + previousHash + merkleRoot;
            // Use a real cryptographic hash function like SHA-256 here
            // For simplicity, I'm using a placeholder "0000000000" as the hash
            return "0000000000";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String calculateMerkleRoot() {
        // Implement Merkle tree logic to compute the Merkle root from transactions
        // This is a simplified example; you can use a library or write your own logic
        List<Integer> transactionHashes = new ArrayList<>();
        for (Transaction tx : transactions) {
            int txHash = tx.hashCode(); // Replace with actual hash function
            transactionHashes.add(txHash);
        }

        return buildMerkleTree(transactionHashes);
    }

    private String buildMerkleTree(List<Integer> transactionHashes) {
        return null;
    }

    public String getHash() {
        return hash;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public int getIndex() {
        return index;
    }

    public void setPreviousHash(String hash) {
        previousHash = hash;
    }

    // Getter methods for various block attributes

    // Implement block validation if needed
}

class Blockchain {
    private List<Block> chain;

    public Blockchain() {
        chain = new ArrayList<>();
        // Create a genesis block
        Block genesisBlock = createGenesisBlock();
        chain.add(genesisBlock);
    }

    private Block createGenesisBlock() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("Genesis", "Alice", 100.0));
        return new Block(0, transactions, "0");
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Block newBlock) {
        // Implement block addition with proper linking
        newBlock.setPreviousHash(getLatestBlock().getHash());
        chain.add(newBlock);
    }

    public List<Block> getChain() {
        return chain;
    }

    // Implement methods to validate blocks and handle transactions

    // Implement interface integration (e.g., API, CLI, or GUI)
}

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain();

        // Example: Add a new block with transactions
        List<Transaction> newTransactions = new ArrayList<>();
        newTransactions.add(new Transaction("Alice", "Bob", 10.0));
        newTransactions.add(new Transaction("Bob", "Charlie", 5.0));

        Block newBlock = new Block(blockchain.getLatestBlock().getIndex() + 1, newTransactions, blockchain.getLatestBlock().getHash());

        // Validate and add the new block to the blockchain
        blockchain.addBlock(newBlock);

        // Print information about the blockchain
        for (Block block : blockchain.getChain()) {
            System.out.println("Block Index: " + block.getIndex());
            System.out.println("Block Hash: " + block.getHash());
            System.out.println("Merkle Root: " + block.getMerkleRoot());
            System.out.println("----------------------------------------");
        }
    }
}
