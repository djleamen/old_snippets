package softwaresystems;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ResourceView extends JFrame {
    ResourceView rv;

    public ResourceView() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Resource View");
        this.setSize(400, 300);
        rv = this;



        JPanel treePanel = new JPanel();
        treePanel.setLayout(new BorderLayout());

        //create nodes for tree -- sample code
//        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
//        DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("Child 1");
//        DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Child 2");
//        DefaultMutableTreeNode child3 = new DefaultMutableTreeNode("Child 3");
//        root.add(child1);
//        root.add(child2);
//        root.add(child3);
//
//        DefaultMutableTreeNode subchild11 = new DefaultMutableTreeNode("Child of 1.1");
//        DefaultMutableTreeNode subchild12 = new DefaultMutableTreeNode("Child of 1.2");
//        child1.add(subchild11);
//        child1.add(subchild12);
//
//
        // create JTree

        DefaultMutableTreeNode root = buildResourceTree("input");
        JTree tree = new JTree(root);
        JScrollPane treeScrollPane = new JScrollPane(tree);
        treePanel.add(treeScrollPane, BorderLayout.CENTER);
        this.add(treePanel, BorderLayout.CENTER);

    }

    public DefaultMutableTreeNode buildResourceTree(String folderName) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(folderName);
        
        if (resource == null) {
            throw new IllegalArgumentException("Folder not found: " + folderName);
        }

        File folder;
//      File folder = new File(folderName);
        try {
            folder = new File(resource.toURI());
        } catch (Exception e) {
            throw new RuntimeException("Error accessing folder: " + folderName, e);
        }
        
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Invalid folder: " + folderName);
        }

        return buildTreeNode(folder);
    }

    private DefaultMutableTreeNode buildTreeNode(File folder) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(folder.getName());
        // TODO: get all folder and files recursively
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // call itself for this folder
                    node.add(buildTreeNode(file));
                } else {
                    // it's  file so add it as a node
                    node.add(new DefaultMutableTreeNode(file.getName()));
                }  
            }
        }
        return node;
    }


    public static void main(String[] args) {
        ResourceView resourceView = new ResourceView();
        resourceView.setVisible(true);
    }
}