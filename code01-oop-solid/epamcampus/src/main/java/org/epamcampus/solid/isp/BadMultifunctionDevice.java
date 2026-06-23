package org.epamcampus.solid.isp;

// ❌ FAT interface — forces ALL implementors to know about ALL capabilities
// A simple printer must "implement" scan, fax, copy, staple even if it cannot do them
public interface BadMultifunctionDevice {
    void print(Document doc);
    void scan(Document doc);
    void fax(Document doc, String destination);
    void copy(Document doc, int copies);
    void staple(Document doc);  // Does your $30 printer staple? No.
}
