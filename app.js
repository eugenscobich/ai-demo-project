// Data models
let boards = [];
let pieces = [];
let cutWidth = 0;

// DOM elements
const boardsTableBody = document.querySelector('#boards-table tbody');
const piecesTableBody = document.querySelector('#pieces-table tbody');
const boardSelect = document.getElementById('piece-board');
const canvas = document.getElementById('boards-canvas');
const ctx = canvas.getContext('2d');

document.getElementById('add-board-form').addEventListener('submit', function(e) {
    e.preventDefault();
    const name = document.getElementById('board-name').value;
    const height = parseInt(document.getElementById('board-height').value);
    const width = parseInt(document.getElementById('board-width').value);
    const qty = parseInt(document.getElementById('board-qty').value);
    for (let i = 0; i < qty; i++) {
        boards.push({ name, height, width, pieces: [] });
    }
    renderBoardsTable();
    updateBoardSelect();
    arrangeAndDraw();
    this.reset();
});

document.getElementById('add-piece-form').addEventListener('submit', function(e) {
    e.preventDefault();
    const name = document.getElementById('piece-name').value;
    const height = parseInt(document.getElementById('piece-height').value);
    const width = parseInt(document.getElementById('piece-width').value);
    const qty = parseInt(document.getElementById('piece-qty').value);
    const boardIdx = parseInt(document.getElementById('piece-board').value);
    for (let i = 0; i < qty; i++) {
        pieces.push({ name, height, width, boardIdx });
    }
    renderPiecesTable();
    arrangeAndDraw();
    this.reset();
});

document.getElementById('arrange-btn').addEventListener('click', function() {
    cutWidth = parseInt(document.getElementById('cut-width').value) || 0;
    arrangeAndDraw();
});

function renderBoardsTable() {
    boardsTableBody.innerHTML = '';
    boards.forEach((b, idx) => {
        boardsTableBody.innerHTML += `<tr><td>${b.name}</td><td>${b.height}</td><td>${b.width}</td><td>1</td><td><button class='btn btn-sm btn-danger' onclick='removeBoard(${idx})'>x</button></td></tr>`;
    });
}

function renderPiecesTable() {
    piecesTableBody.innerHTML = '';
    pieces.forEach((p, idx) => {
        piecesTableBody.innerHTML += `<tr><td>${p.name}</td><td>${p.height}</td><td>${p.width}</td><td>1</td><td>${boards[p.boardIdx]?.name||''}</td><td><button class='btn btn-sm btn-danger' onclick='removePiece(${idx})'>x</button></td></tr>`;
    });
}

function updateBoardSelect() {
    boardSelect.innerHTML = '';
    boards.forEach((b, idx) => {
        boardSelect.innerHTML += `<option value='${idx}'>${b.name}</option>`;
    });
}

window.removeBoard = function(idx) {
    boards.splice(idx, 1);
    renderBoardsTable();
    updateBoardSelect();
    arrangeAndDraw();
}

window.removePiece = function(idx) {
    pieces.splice(idx, 1);
    renderPiecesTable();
    arrangeAndDraw();
}

function arrangeAndDraw() {
    // Assign pieces to their boards
    boards.forEach(b => b.pieces = []);
    pieces.forEach(p => {
        if (boards[p.boardIdx]) boards[p.boardIdx].pieces.push(p);
    });
    drawBoards();
}

function drawBoards() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    let x = 20, y = 20, maxRowHeight = 0;
    boards.forEach((b, idx) => {
        // Draw board
        let scale = Math.min(300 / b.width, 200 / b.height);
        let bw = b.width * scale, bh = b.height * scale;
        if (x + bw > canvas.width) {
            x = 20;
            y += maxRowHeight + 40;
            maxRowHeight = 0;
        }
        ctx.strokeStyle = '#333';
        ctx.strokeRect(x, y, bw, bh);
        ctx.font = '14px Arial';
        ctx.fillText(b.name, x + 4, y + 18);
        // Arrange pieces in simple greedy way, row by row
        let rowY = y + cutWidth * scale;
        let remH = bh - cutWidth * scale;
        let pieceIdx = 0;
        while (remH > 0.5 * scale && pieceIdx < b.pieces.length) {
            let rowHeight = 0;
            let rowX = x + cutWidth * scale;
            let remW = bw - cutWidth * scale;
            while (remW > 0.5 * scale && pieceIdx < b.pieces.length) {
                let p = b.pieces[pieceIdx];
                let ph = p.height * scale, pw = p.width * scale;
                if (ph + cutWidth * scale > remH || pw + cutWidth * scale > remW) break;
                ctx.fillStyle = getColor(idx, pieceIdx);
                ctx.fillRect(rowX, rowY, pw, ph);
                ctx.strokeStyle = '#222';
                ctx.strokeRect(rowX, rowY, pw, ph);
                ctx.fillStyle = '#fff';
                ctx.fillText(p.name, rowX + 2, rowY + ph / 2);
                rowX += pw + cutWidth * scale;
                remW -= pw + cutWidth * scale;
                rowHeight = Math.max(rowHeight, ph);
                pieceIdx++;
            }
            rowY += rowHeight + cutWidth * scale;
            remH -= rowHeight + cutWidth * scale;
        }
        x += bw + 40;
        maxRowHeight = Math.max(maxRowHeight, bh);
    });
}

function getColor(bi, pi) {
    // Return a pastel color based on board and piece indices
    const colors = [
        'rgba(255,182,193,0.55)', 'rgba(152,251,152,0.55)', 'rgba(135,206,250,0.55)',
        'rgba(255,255,180,0.55)', 'rgba(255,204,153,0.55)', 'rgba(216,191,216,0.55)'
    ];
    return colors[(bi + pi) % colors.length];
}

renderBoardsTable();
renderPiecesTable();
updateBoardSelect();
arrangeAndDraw();
