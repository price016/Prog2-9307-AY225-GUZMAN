/**
 * =====================================================
 * Student Name    : GUZMAN, FRANCESS PRICEAH S.
 * Course          : Math 101 — Linear Algebra
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 16, 2026
 * GitHub Repo     : https://github.com/price016/uphsd-cs-guzman-priceah.git
 *
 * Description:
 *   This program computes the determinant of a hardcoded 3x3 matrix assigned
 *   to GUZMAN, FRANCESS PRICEAH S. for Math 101. The solution is computed using cofactor
 *   expansion along the first row. Each intermediate step (2x2 minor,
 *   cofactor term, running sum) is printed to the console in a readable format.
 *
 * Detailed algorithm notes (added for clarity):
 *  - Cofactor expansion (Laplace expansion) along the first row:
 *      det(M) = m00 * det(M11) - m01 * det(M12) + m02 * det(M13)
 *    where Mij denotes the 2x2 submatrix formed by removing row i and
 *    column j from the original 3x3 matrix.
 *  - Each 2x2 minor is computed using the formula det([[a,b],[c,d]]) = a*d - b*c.
 *  - The sign pattern for cofactors alternates by position: + - + on the first row,
 *    which we implement explicitly by negating the second cofactor term.
 *  - Complexity: this direct expansion takes constant time for a 3x3 matrix (O(1)),
 *    but the general cofactor expansion for an n×n matrix is O(n!). For larger
 *    matrices use optimized methods (LU decomposition, Gaussian elimination).
 * =====================================================
 */

public class DeterminantSolver {

    // ── SECTION 1: Matrix Declaration ───────────────────────────────────
    // This 3×3 matrix is the input for the determinant calculation.
    // The matrix is stored as a 2D integer array in row-major order.
    //      | 7  2  1 |
    //  M = | 3  5  4 |
    //      | 2  1  6 |
    // The determinant will be computed using cofactor expansion
    // along the first row of this matrix.

    static int[][] matrix = {
        { 7, 2, 1 },   // Row 1
        { 3, 5, 4 },   // Row 2
        { 2, 1, 6 }    // Row 3
    };

    // ── SECTION 2: 2×2 Determinant Helper ───────────────────────────────
    // Computes the determinant of a 2×2 matrix.
    // If the 2×2 matrix is:
    // | a  b |
    // | c  d |
    // then:
    // det = (a × d) − (b × c)
    // This helper method is used to compute the minors needed
    // for the 3×3 determinant using cofactor expansion.

    static int computeMinor(int a, int b, int c, int d) {
        return (a * d) - (b * c);
    }

    // ── SECTION 3: Matrix Printer ────────────────────────────────────────
    // Displays the matrix in a clean formatted layout so the user
    // can clearly see the matrix whose determinant will be computed.

    static void printMatrix(int[][] m) {
        System.out.println("┌               ┐");
        for (int[] row : m) {
            System.out.printf("│  %2d  %2d  %2d  │%n", row[0], row[1], row[2]);
        }
        System.out.println("└               ┘");
    }

    static void solveDeterminant(int[][] m) {

        System.out.println("=".repeat(52));
        System.out.println("  3x3 MATRIX DETERMINANT SOLVER");
        System.out.println("  Student: GUZMAN, FRANCESS PRICEAH S.");
        System.out.println("  Assigned Matrix: 7 2 1 | 3 5 4 | 2 1 6");
        System.out.println("=".repeat(52));
        printMatrix(m);
        System.out.println("=".repeat(52));

        // ── Step 1: Compute Minor M₁₁ ──
        // For cofactor expansion along the first row, we first compute M₁₁.
        // M₁₁ is obtained by removing:
        //   • Row 1
        //   • Column 1
        // Remaining 2×2 matrix:
        // | m[1][1]  m[1][2] |
        // | m[2][1]  m[2][2] |
        int minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
        System.out.printf("  Step 1 — Minor M₁₁: det([%d,%d],[%d,%d]) = (%d×%d)-(%d×%d) = %d%n",
            m[1][1], m[1][2], m[2][1], m[2][2],
            m[1][1], m[2][2], m[1][2], m[2][1], minor11);

        // ── Step 2: Compute Minor M₁₂ ──
        // Remove:
        //   • Row 1
        //   • Column 2
        // Remaining 2×2 matrix:
        // | m[1][0]  m[1][2] |
        // | m[2][0]  m[2][2] |
        int minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
        System.out.printf("  Step 2 — Minor M₁₂: det([%d,%d],[%d,%d]) = (%d×%d)-(%d×%d) = %d%n",
            m[1][0], m[1][2], m[2][0], m[2][2],
            m[1][0], m[2][2], m[1][2], m[2][0], minor12);

        // ── Step 3: Compute Minor M₁₃ ──
        // Remove:
        //   • Row 1
        //   • Column 3
        // Remaining 2×2 matrix:
        // | m[1][0]  m[1][1] |
        // | m[2][0]  m[2][1] |
        int minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);
        System.out.printf("  Step 3 — Minor M₁₃: det([%d,%d],[%d,%d]) = (%d×%d)-(%d×%d) = %d%n",
            m[1][0], m[1][1], m[2][0], m[2][1],
            m[1][0], m[2][1], m[1][1], m[2][0], minor13);

        // ── Step 4: Compute Cofactor Terms ──
        // Cofactor expansion along the first row uses the sign pattern:
        //  +  −  +
        // Therefore:
        // C₁₁ = + m[0][0] × M₁₁
        // C₁₂ = − m[0][1] × M₁₂
        // C₁₃ = + m[0][2] × M₁₃
        int c11 =  m[0][0] * minor11;
        int c12 = -m[0][1] * minor12;
        int c13 =  m[0][2] * minor13;

        System.out.println();
        System.out.printf("  Cofactor C₁₁ = (+1) × %d × %d = %d%n", m[0][0], minor11, c11);
        System.out.printf("  Cofactor C₁₂ = (-1) × %d × %d = %d%n", m[0][1], minor12, c12);
        System.out.printf("  Cofactor C₁₃ = (+1) × %d × %d = %d%n", m[0][2], minor13, c13);

        // ── Step 5: Compute the Determinant ──
        // The determinant of the 3×3 matrix is obtained by summing
        // the three cofactor terms:
        // det(M) = C₁₁ + C₁₂ + C₁₃
        int det = c11 + c12 + c13;
        System.out.printf("%n  det(M) = %d + (%d) + %d%n", c11, c12, c13);
        System.out.println("=".repeat(52));
        System.out.printf("  ✓  DETERMINANT = %d%n", det);

        // ── Final Step: Check if Matrix is Singular ──
        // If det(M) = 0, the matrix is singular,
        // meaning it does not have an inverse.
        if (det == 0) {
            System.out.println("  ⚠ The matrix is SINGULAR — it has no inverse.");
        }
        System.out.println("=".repeat(52));
    }

    // ── SECTION 5: Entry Point ───────────────────────────────────────────
    // The main method is the program's entry point.
    // It calls solveDeterminant() with the student's assigned matrix.
    
    public static void main(String[] args) {
        solveDeterminant(matrix);
    }
}