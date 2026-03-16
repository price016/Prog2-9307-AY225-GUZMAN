#!/usr/bin/env node

/**
 * =====================================================
 * Student Name    : GUZMAN, FRANCESS PRICEAH S.
 * Course          : Math 101 — Linear Algebra
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : March 16, 2026
 * GitHub Repo     : https://github.com/price016/uphsd-cs-guzman-priceah.git
 * Runtime         : Node.js (run with: node determinant_solver.js)
 *
 * Description:
 *   JavaScript equivalent of DeterminantSolver.java. This script computes
 *   the determinant of the same hardcoded 3x3 matrix using cofactor expansion
 *   along the first row. All intermediate steps are logged to the console
 *   using console.log() for complete transparency of the solution process.
 * =====================================================
 */

// ── SECTION 1: Matrix Declaration ───────────────────────────────────
// The 3×3 matrix used in this program is stored as a nested JavaScript array.
// Each inner array represents a row of the matrix.
//      | 7  2  1 |
//  M = | 3  5  4 |
//      | 2  1  6 |
// The determinant of this matrix will be computed using
// cofactor expansion along the first row.

const matrix = [
    [ 7, 2, 1],   // Row 1
    [ 3, 5, 4],   // Row 2
    [ 2, 1, 6]    // Row 3
];

// ── SECTION 2: Matrix Printer ────────────────────────────────────────
// Prints the matrix in a readable box-style format so the user
// can clearly see the matrix whose determinant is being computed.
// This function only handles display and does not perform calculations.

function printMatrix(m) {
    console.log(`┌               ┐`);
    m.forEach(row => {
        const fmt = row.map(v => v.toString().padStart(3)).join("  ");
        console.log(`│ ${fmt}  │`);
    });
    console.log(`└               ┘`);
}

// ── SECTION 3: 2×2 Determinant Helper ───────────────────────────────
// Computes the determinant of a 2×2 matrix.
// If the matrix is:
// | a  b |
// | c  d |
// then its determinant is:
// det = (a × d) − (b × c)
// This helper function is used to calculate the minors
// needed during the 3×3 cofactor expansion.

function computeMinor(a, b, c, d) {
    // 2x2 determinant formula: ad - bc
    return (a * d) - (b * c);
}

// ── SECTION 4: Step-by-Step Determinant Solver ──────────────────────
// This function performs the full determinant calculation using
// cofactor expansion along the first row.
// The algorithm follows these steps:
// 1. Display the matrix.
// 2. Compute the three 2×2 minors (M₁₁, M₁₂, M₁₃).
// 3. Apply the cofactor sign pattern (+ − +).
// 4. Multiply each minor by its corresponding element from the first row.
// 5. Add the cofactor terms to obtain the determinant.
// 6. Display the final result and check if the matrix is singular.

function solveDeterminant(m) {
    const line = "=".repeat(52);

    // Print problem header
    console.log(line);
    console.log("  3x3 MATRIX DETERMINANT SOLVER");
    console.log("  Student: GUZMAN, FRANCESS PRICEAH S.");
    console.log("  Assigned Matrix: 7 2 1 | 3 5 4 | 2 1 6");
    console.log(line);
    printMatrix(m);
    console.log(line);
    console.log();
    console.log("Expanding along Row 1 (cofactor expansion):");
    console.log();

    // ── Step 1: Compute Minor M₁₁ ──
    // To compute M₁₁, remove the first row and first column.
    // The remaining 2×2 matrix is:
    // | m[1][1]  m[1][2] |
    // | m[2][1]  m[2][2] |
    // The determinant of this 2×2 matrix becomes the value of M₁₁.
    const minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
    console.log(
        `  Step 1 — Minor M₁₁: det([${m[1][1]},${m[1][2]}],[${m[2][1]},${m[2][2]}])` +
        ` = (${m[1][1]}×${m[2][2]}) - (${m[1][2]}×${m[2][1]}) = ${minor11}`
    );

    // ── Step 2: Compute Minor M₁₂ ──
    // Remove the first row and second column.
    // The remaining 2×2 matrix is:
    // | m[1][0]  m[1][2] |
    // | m[2][0]  m[2][2] |
    const minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
    console.log(
        `  Step 2 — Minor M₁₂: det([${m[1][0]},${m[1][2]}],[${m[2][0]},${m[2][2]}])` +
        ` = (${m[1][0]}×${m[2][2]}) - (${m[1][2]}×${m[2][0]}) = ${minor12}`
    );

    // ── Step 3: Compute Minor M₁₃ ──
    // Remove the first row and third column.
    // The remaining 2×2 matrix is:
    // | m[1][0]  m[1][1] |
    // | m[2][0]  m[2][1] |
    const minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);
    console.log(
        `  Step 3 — Minor M₁₃: det([${m[1][0]},${m[1][1]}],[${m[2][0]},${m[2][1]}])` +
        ` = (${m[1][0]}×${m[2][1]}) - (${m[1][1]}×${m[2][0]}) = ${minor13}`
    );

    // ── Step 4: Compute Cofactor Terms ──
    // When expanding along the first row, the cofactor signs follow
    // the alternating pattern:
    //   +   −   +
    // Therefore:
    // C₁₁ = + m[0][0] × M₁₁
    // C₁₂ = − m[0][1] × M₁₂
    // C₁₃ = + m[0][2] × M₁₃
    const c11 =  m[0][0] * minor11;
    const c12 = -m[0][1] * minor12;
    const c13 =  m[0][2] * minor13;

    console.log();
    console.log(`  Cofactor C₁₁ = (+1) × ${m[0][0]} × ${minor11} = ${c11}`);
    console.log(`  Cofactor C₁₂ = (-1) × ${m[0][1]} × ${minor12} = ${c12}`);
    console.log(`  Cofactor C₁₃ = (+1) × ${m[0][2]} × ${minor13} = ${c13}`);

    // ── Final Step: Compute the Determinant ──
    // The determinant of the 3×3 matrix is obtained by summing
    // the three cofactor terms:
    // det(M) = C₁₁ + C₁₂ + C₁₃
    const det = c11 + c12 + c13;
    console.log();
    console.log(`  det(M) = ${c11} + (${c12}) + ${c13}`);
    console.log(line);
    console.log(`  ✓  DETERMINANT = ${det}`);

    // ── Step 6: Singular Matrix Check ──
    // If the determinant equals zero, the matrix is singular.
    // A singular matrix does not have an inverse.
    if (det === 0) {
        console.log("  ⚠ The matrix is SINGULAR — it has no inverse.");
    }
    console.log(line);
}

// ── SECTION 5: Program Entry Point ──────────────────────────────────
// The program begins execution here by calling solveDeterminant()
// with the predefined matrix as input.

solveDeterminant(matrix);

// Export for programmatic use (allows requiring this file in other scripts/tests)
if (typeof module !== 'undefined' && module.exports) {
    module.exports = { solveDeterminant };
}
